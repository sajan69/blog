// Format date utility function
function formatDate(dateString) {
    return new Date(dateString).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
}

// Show success message
function showSuccess(message) {
    Swal.fire({
        title: 'Success!',
        text: message,
        icon: 'success',
        confirmButtonColor: '#28a745'
    });
}

// Show error message
function showError(message) {
    Swal.fire({
        title: 'Error!',
        text: message,
        icon: 'error',
        confirmButtonColor: '#dc3545'
    });
}

// Show confirmation dialog
function showConfirmation(title, text) {
    return Swal.fire({
        title: title,
        text: text,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#dc3545',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'Cancel'
    });
} 

//current year
document.getElementById('currentYear').textContent = new Date().getFullYear();